#This script takes the question/answer pairs from the below website and parses
#  them into a json

# The last entry shouldn't print a comma, I've been removing it by hand

from lxml import html
import requests
import json

f = open('datastructureQs.json', 'w')
f.write("{\n")

for i in range(1,21):
	page = requests.get('http://www.careerride.com/question-' + str(i) + '-Data-structure')
	tree = html.fromstring(page.text)

	question = tree.xpath('//span[@class="question"]/text()')
	answers = tree.xpath('//span[@class="answer"]/text()')
	answers = [x[3:] for x in answers]

	answers[-1] = answers[-1][16:]
	# for i, answer in enumerate(answers):
	# 	if answer == answers[-1][16:]:
	# 		answers += str(i)
	# del answers[-2]
	answers = json.dumps(answers)
	print(question)
	print(answers)
	f.write('    "' + str(question[0]) + '":\n        ' + str(answers) + ",\n")

f.write("}")