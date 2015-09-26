from lxml import html
import requests

page = requests.get('http://www.careerride.com/test.aspx?type=Data-structure')
tree = html.fromstring(page.text)

question = tree.xpath('//span[@class="question"]/text()')
answers = tree.xpath('//span[@class="answer"]/text()')

print(question)
print(answers)