from __future__ import division
import math
import random
import sys
import os
import json
execfile("NeuralNetwork.py")


def neural_network_execute(examples, network):
	## inputs
	## a set of examples, each with input vextor x and output vector y
	## network, a multilayer network with L layers, weights activation function g

	result = [None] * len(examples)
	index = 0

	for x,y in examples:
		for i in range(len(network.getInputLayer())):
			if i != 0 :
				network.Layers[2][i].Value = x[i-1] ## get the x vector of the examples

		## COMPUTES THE OUTPUT
		for l in range(len(network.Layers)-2, -1,-1):
			for node in network.Layers[l]:
				if not node.Bias:
					total = totalSum(network,l,node)
					node.Value = network.sigmoid(total)

		## OUTPUT TEST
		maxIndex = network.getMaxOutputNode()
		result[index] = maxIndex +1
		index += 1

	return result

def totalSum(network, l, node):
	totalSum = 0.0
	for prevNode in network.Layers[l+1]:
		totalSum = (prevNode.Weights[network.getNodeIndex(node)] * prevNode.Value) + totalSum
	return totalSum

def getExamplesFromFile(filePath):
	examples = list()
	fo = open(filePath)
	for line in fo:
		i = 0
		x = []
		y = []
		aux = line.split(",")
		for word in aux:
			if i == 2:
				y = float(word)
			else:
				x.append(float(word))
			i = i + 1
		trainingData = []
		trainingData.append(x)
		trainingData.append(y)
		examples.append(trainingData)
	return examples

def showStatistic(result, examples):
	# The number of classification errors, recognition rate (% correct) and profit obtained.
	numberOfErrors = 0
	numberOfCorrection = 0
	correctList = list()
	errorList = list()
	count = 0.0
	confusionMatrix = dict()
	for i in range(len(result)):
		if result[i] == examples[i][1]:
			numberOfCorrection += 1
			correctList.append(result[i])
		else:
			numberOfErrors += 1
			errorList.append(result[i])
		if not examples[i][1] in confusionMatrix:
			confusionMatrix[examples[i][1]] = list()
		confusionMatrix[examples[i][1]].append(result[i])

	print("result : "),
	print(result)
	print("Number of classification errors : " + str(numberOfErrors))
	print("Recognition rate (% correct) : " + str(((numberOfCorrection*100)/len(result))))

	totalProfit = 0.0

	correctMatrix = {1:0.20, 2:0.15, 3:0.05, 4:-0.03}
	errorMatrix = {1:0.07, 2:0.07, 3:0.07, 4:0.03}
	
	correctProfit = 0.0
	errorDiscount = 0.0
	for label in correctList:
		correctProfit += correctMatrix[label]
	for label in errorList:
		errorDiscount += errorMatrix[label]
	totalProfit = correctProfit - errorDiscount

	print("Profit obtained : " + str(totalProfit))
	print("Confusion Matrix: ")
	print(confusionMatrix)

## PLOT CHART (NOT WORKING YET)
def plotCharts():
	x = [1, 2, 3, 4]
	y = [1, 4, 9, 6]
	labels = ['Frogs', 'Hogs', 'Bogs', 'Slogs']

	plt.plot(x, y, 'ro')
	# You can specify a rotation for the tick labels in degrees or with keywords.
	plt.xticks(x, labels, rotation='vertical')
	# Pad margins so that markers don't get clipped by the axes
	plt.margins(0.2)
	# Tweak spacing to prevent clipping of tick-labels
	plt.subplots_adjust(bottom=0.15)
	plt.show()

def main():
	if len(sys.argv) != 3:
		print("Wrong call")
		print("python logreg.py examples_path")
		return
	examples = getExamplesFromFile(sys.argv[2])
	fo = open(sys.argv[1],"rb")
	schema = NeuralNetworkSchema()
	result = schema.load(json.load(fo))
	network = result.data
	result = neural_network_execute(examples,network)
	showStatistic(result, examples)


main()