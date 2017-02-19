from marshmallow import Schema, fields, pprint

class Node:
	Weights = None
	Bias = None
	Value = 0.0

	def __init__(self, relations, bias = False):
		self.Weights = dict()
		if len(relations) != 0:
			for node in relations:
				if not node.Bias:
					self.Weights[relations.index(node)] = random.uniform(-1,1)		
		self.Bias = bias
			


class WeightSchema(fields.Field):
    
    def _serialize(self, value, attr, obj):
        if value is None:
            return ''
        return obj.Weights

    def _deserialize(self, value):
    	result = dict()
    	for key in value:
    		result[int(key)] = value[key]
        return result

class NeuralNodeSchema(Schema):
	Weights = WeightSchema(attribute="Weights")
	Bias = fields.Boolean()
	Value = fields.Float()

	def make_object(self, data):
		node = Node(list())
		node.Weights = data["Weights"]
		node.Bias = data["Bias"]
		node.Value = data["Value"]
		return node
