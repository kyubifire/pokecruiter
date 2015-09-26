from lxml import html
import requests

page = requests.get('http://www.careerride.com/test.aspx?type=Data-structure')
tree = html.fromstring(page.text)

question = tree.xpath('//span[@class="question"]/text()')
answers = tree.xpath('//label[@for="rblAnswer_0"]/text()')
answers += tree.xpath('//label[@for="rblAnswer_1"]/text()')
answers += tree.xpath('//label[@for="rblAnswer_2"]/text()')
answers += tree.xpath('//label[@for="rblAnswer_3"]/text()')

print(question)
print(answers)