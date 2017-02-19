from __future__ import division
import math
import random
import sys
import os
import json
import matplotlib.pyplot as plt
import numpy as np

execfile("NeuralNetwork.py")


LEARNING_RATE = 0.1
ERROR_RATE = 0.2
filePath = None
fileName = None
graph = [list(), list()]

def back_prop_learning(examples, network):
	## inputs
	## a set of examples, each with input vextor x and output vector y
	## network, a multilayer network with L layers, weights activation function g

	errors = dict()
	iteration = 0

	while True:

		## SAVES THE NEURAL NETWORK IN 0, 10, 100, 1000, 10.000, ...
		if (iteration == 0) or (iteration != 1 and isPower(iteration, 10)):
			fi = open(filePath + "//neuralTrain" + str(iteration) + ".txt", 'w+')
			serialize(network, fi)

		for x,y in examples:
			for i in range(len(network.getInputLayer())):
				if i != 0 :
					network.Layers[2][i].Value = x[i-1] ## get the x vector of the examples

			## COMPUTES THE NODES VALUES AND THE OUTPUT (TRAIN THE NETWORK)
			for l in range(len(network.Layers)-2, -1,-1):
				for node in network.Layers[l]:
					if not node.Bias:
						total = totalSum(network,l,node)
						node.Value = network.sigmoid(total)

			## ERROR CALCULATION FOR THE OUTPUT LAYER
			for node in network.getOutputLayer():
				if network.getNodeIndex(node) == (y-1):
					target = 1
				else:
					target = 0
				errors[node] = ((node.Value*(1-node.Value))*(target - node.Value))
			## ERROR CALCULATION FOR THE OTHER LAYERS
			for l in range(len(network.Layers)-2,3):
				for node in network.Layers[l]:
					if not node.Bias:
						errors[node] = ((node.Value*(1-node.Value)) *  sumErrorsWeights(network, errors,node, l))

			## UPDATE WEIGHTS USING DELTA
			for l in range(len(network.Layers)):
				for node in network.Layers[l]:
					for key in node.Weights:
						node.Weights[key] = node.Weights[key] + (LEARNING_RATE * node.Value * errors[network.Layers[l-1][key]])
		
		graph[0].append(iteration)	## get the interation to plot the graph
		graph[1].append(sumAllErrors(errors)) ## get the sum of errors to plot the graph
		iteration += 1 ## counts the epoch

		if stop_criterion(errors):
			break
	return network ## returns the trained neural network


def serialize(network, fileDestination):
	schema = NeuralNetworkSchema()
	content = schema.dump(network)
	json.dump(content.data, fileDestination)


def stop_criterion(errors): ## responsable to verify if the stop criterion was reached
	status = True
	if sumAllErrors(errors) >= ERROR_RATE:
		status = False
	return status

def totalSum(network, l, node):
	totalSum = 0.0
	for prevNode in network.Layers[l+1]:
		totalSum = (prevNode.Weights[network.getNodeIndex(node)] * prevNode.Value) + totalSum
	return totalSum

def sumErrorsWeights(network, errors, node, layer):
	total = 0.0
	for key in node.Weights:
		total = (node.Weights[key] * errors[network.Layers[layer-1][key]]) + total
	return total

def sumAllErrors(errors): ## sums the error of all nodes
	total = 0
	for key in errors:
		total += abs(errors[key])
	return total

def getExamplesFromFile(filePath): ## open the examples file
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

def isPower (num, base): ## verify if a given epoch is power of 10
    if base == 1 and num != 1: return False
    if base == 1 and num == 1: return True
    if base == 0 and num != 1: return False
    power = int (math.log (num, base) + 0.5)
    return base ** power == num


def main():
	if len(sys.argv) < 2 or len(sys.argv) > 3:
		print("Wrong call")
		print("python logreg.py examples_path")
		return
	if len(sys.argv) == 3:
		global ERROR_RATE
		ERROR_RATE = float(sys.argv[2])
	global filePath
	global fileName
	filePath, fileName = os.path.split(sys.argv[1])
	examples = getExamplesFromFile(sys.argv[1])
	network = Network()
	network = back_prop_learning(examples,network)
	fi = open(filePath + "//neuralTrainComplete" + ".txt", 'w+')
	serialize(network, fi)

	figure1 = plt.figure()
	ax1 = figure1.add_subplot(111)
	ax1.plot(graph[0], graph[1])
	ax1.set_ylabel('sum of errors')
	ax1.set_xlabel('epoch')

	plt.show()
		

main()