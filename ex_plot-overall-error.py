import numpy as np
import scipy.stats as st
import matplotlib.pyplot as plt


def ConfidenceInterval(values):
  N = len(values)
  Z = 1.96
  std_dev = np.std(values)

  std_error = std_dev / math.sqrt(N)

  return Z * std_error

tunnels = ['RIO450', 'DPT', 'RCLT', 'YBT', 'DMAT']

listMeanOverallGPSError = list()
listMeanOverallDRError = list()
listConfIntOverallGPSError = list()
listConfIntOverallDRError = list()

for tunnel in tunnels:
	
	listGPSError = list()
	listDRError = list()

	#open GPS file
	with open('gps-error-'+tunnel+'.txt') as gpsFile:
		listLines = gpsFile.readlines()
		
		if tunnel == 'RIO450':
			line0 = listLines[0].split('\t')
			for j in range(0,30):
				listGPSError.append(float(line0[j]))
		else:	
			line0 = listLines[0].split('\t')
			line1 = listLines[1].split('\t')
			for j in range(0,30):
				listGPSError.append(np.mean([float(line0[j]),float(line1[j])]))	

	#open DR file
	with open('dr-error-'+tunnel+'.txt') as drFile:
		listLines = drFile.readlines()
		
		if(tunnel == 'RIO450'):
			line0 = listLines[0].split('\t')
			
			for j in range(0,30):
				listDRError.append(float(line0[j]))
		else:
			
			line0 = listLines[0].split('\t')
			line1 = listLines[1].split('\t')
			
			for j in range(0,30):
				listDRError.append(np.mean([float(line0[j]),float(line1[j])]))	

	listMeanOverallGPSError.append(np.mean(listGPSError))
	listMeanOverallDRError.append(np.mean(listDRError))
	interv = st.t.interval(0.95, len(listGPSError)-1, loc=np.mean(listGPSError), scale=st.sem(listGPSError))
	err = (interv[1]-interv[0]) / 2
	listConfIntOverallGPSError.append(err)
	st.t.interval(0.95, len(listDRError)-1, loc=np.mean(listDRError), scale=st.sem(listDRError))
	err = (interv[1]-interv[0]) / 2
	listConfIntOverallDRError.append(err)


# #plotting
# menMeans = (20, 35, 30, 35, 27)
# menStd = (2, 3, 4, 1, 2)
# womenMeans = (25, 32, 34, 20, 25)
# womenStd = (3, 5, 2, 3, 3)

print(listMeanOverallGPSError)
print(listMeanOverallDRError)
print(listConfIntOverallGPSError)
print(listConfIntOverallDRError)
#exit()



N = len(listMeanOverallGPSError)# number of data entries
ind = np.arange(N)              # the x locations for the groups
width = 0.35                    # bar width

fig, ax = plt.subplots()

rects1 = ax.bar(ind, listMeanOverallGPSError,                  # data
                width,                          # bar width
                color='limegreen',        # bar colour
                #yerr=listConfIntOverallGPSError,                  # data for error bars
                error_kw={'ecolor':'black',    # error-bars colour
                          'linewidth':2})       # error-bar width

rects2 = ax.bar(ind + width, listMeanOverallDRError, 
                width, 
                color='coral', 
                #yerr=listConfIntOverallDRError, 
                error_kw={'ecolor':'black',
                          'linewidth':2})

axes = plt.gca()
#axes.set_ylim([0, 41])             # y-axis bounds

ax.set_ylabel('Mean Error (m)')
ax.set_title('Mean Overall Error - GPS vs. GPS+GDR')
ax.set_xticks(ind + width)
#ax.set_yticks(np.arange(0,2600))
ax.set_xticklabels(('Rio450', 'DPT', 'RCLT', 'YBT', 'DMAT'))


ax.legend((rects1[0], rects2[0]), ('GPS', 'GPS+GDR'))

def autolabel(rects):
    for rect in rects:
        height = rect.get_height()
        ax.text(rect.get_x() + rect.get_width()/2., 1.05*height,
                '%d' % int(height),
                ha='center',            # vertical alignment
                va='bottom'             # horizontal alignment
                )

autolabel(rects1)
autolabel(rects2)

plt.xticks(rotation='45')

axes = plt.gca()
axes.set_ylim([0,2650])

fig.savefig('gpsvsDROverallError.png',dpi=200)

#plt.show()                              # render the plot
