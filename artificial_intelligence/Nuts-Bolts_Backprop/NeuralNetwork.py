from marshmallow import Schema, fields, pprint
execfile("NeuralNode.py")

class Network:
	Layers = list()

	def __init__(self, Layers=None):
		if Layers == None:
			outputLayer = [Node(list()), Node(list()), Node(list()), Node(list())]
			self.Layers.append(outputLayer) ## output layer
			
			hiddenBias = Node(outputLayer, True)
			hiddenBias.Value = 1.0
			hiddenLayer = [ hiddenBias, Node(outputLayer), Node(outputLayer), Node(outputLayer),Node(outputLayer),Node(outputLayer)]
			self.Layers.append(hiddenLayer) ## hidden layer
			
			inputBias = Node(hiddenLayer, True)
			inputBias.Value = 1.0
			inputLayer = [inputBias, Node(hiddenLayer), Node(hiddenLayer)]
			self.Layers.append(inputLayer) ## input layer
		else:
			self.Layers = Layers


	def sigmoid(self, x):
		value = (1/(1+(math.exp( x*(-1)))))
		return value

	def getInputLayer(self):
		return self.Layers[2]

	def getHiddenLayer(self):
		return self.Layers[1]

	def getOutputLayer(self):
		return self.Layers[0]

	def getWeights(self):
		weightList = list()
		i =  0 ## list index
		for layer in self.Layers:
			for node in layer:
				aux = dict()
				for key in node.Weights:
					aux[self.fibonacci(self.Layers.index(layer)-1) + self.getNodeIndex(key) ] = node.Weights[key] ## this way I can get order all nodes in the network in a list beginning with output
				weightList.append(aux)
		return weightList

	def fibonacci(self, n):
	    if n == 0: return 0
	    elif n == 1: return 1
	    else: return F(n-1)+F(n-2)

	def getNodeIndex(self, node):
	 	for i in  range(len(self.Layers)):
	 		for j in range(len(self.Layers[i])):
	 			if self.Layers[i][j] == node:
	 				return j

	def getMaxOutputNode(self):
		maxValue = float("-inf")
		maxIndex = -1
		for index in range(len(self.getOutputLayer())):
			node = self.getOutputLayer()[index]
			if node.Value >= maxValue:
				maxValue = node.Value
				maxIndex = index
		return maxIndex

class NeuralNetworkSchema(Schema):
	Layers = fields.List(fields.List(fields.Nested(NeuralNodeSchema)))

	def make_object(self, data):
		return Network(**data)