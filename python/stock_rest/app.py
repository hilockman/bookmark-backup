#!flask/bin/python
from flask import Flask, jsonify
import tushare as ts

app = Flask(__name__)

tasks = [
    {
        'id': 1,
        'title': u'Buy groceries',
        'description': u'Milk, Cheese, Pizza, Fruit, Tylenol', 
        'done': False
    },
    {
        'id': 2,
        'title': u'Learn Python',
        'description': u'Need to find a good Python tutorial on the web', 
        'done': False
    }
]

@app.route('/tasks', methods=['GET'])
def get_tasks():
    return jsonify({'tasks': tasks})

@app.route('/')
def index():
    return "Hello, World!"
	
@app.route('/stock/histData/<string:task_id>',methods=['GET'])
def get_hist_data(task_id):
    return ts.get_hist_data(task_id).to_json()

if __name__ == '__main__':
    app.run(debug=True)
	