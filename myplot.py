
import math
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages
import numpy as np

def confidence_interval(values):
    N = len(values)
    Z = 1.96
    std_dev = np.std(values)
    
    std_error = std_dev / math.sqrt(N)
    
    return Z * std_error

nc = [4,9,14,19,24]	

tmp1 =list()
Del1 = list()
Del1_CONF = list()


	
def mydata():
	for c in nc:
		with open("output_"+str(c)+"_1.dat") as f:
			listLines = f.readlines()
			for i in range(0, len(listLines),2):
				line = listLines[i].split('\t')
				tmp1.append(float(line[0]))
			Del1.append(np.mean(tmp1))
			Del1_CONF.append(confidence_interval(tmp1))
		del tmp1[:]
	print Del1
	print Del1_CONF
	
	plt.errorbar(nc,Del1,color='#66bb6a',yerr=Del1_CONF,marker='o',linestyle='-', label='Delay1', markersize =3,linewidth = 2)
	plt.xlabel('Comp')
	#plt.xticks(np.arange(1, 250, 24))
	#plt.xscale('log')
	#plt.yscale('log')
	plt.ylabel('Delay (ms)')
	#plt.legend(loc='upper right', bbox_to_anchor=(0.175, 1.016), fancybox=True, shadow=True, fontsize='small')

	fig = plt.gcf()
	#fig.suptitle('Error Per Cycle',y=0.98)
	#plt.show()
	plt.draw()
	#fig.savefig('Delay1.png',dpi=200)
	#plt.show()
	#plt.clf()
	fig.savefig('packet_loss.eps')
	pdf = PdfPages('packet_loss.pdf')
	pdf.savefig(dpi=200)
	pdf.close()
   

if __name__ == "__main__":
    pass
    
    mydata()