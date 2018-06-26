#!flask/bin/python
from flask import Flask, jsonify
import tushare as ts
import sys
from flask import request


def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()

app = Flask(__name__)


@app.route('/')
def index():
    return "Hello, World!"
	
@app.route('/stock/histData/<string:task_id>',methods=['GET'])
def get_hist_data(task_id):
    return ts.get_hist_data(task_id).to_json()

@app.route('/stock/basics',methods=['GET'])
def get_stock_basics():
    basics = ts.get_stock_basics()
    basics.to_excel('d:/stock/stock_basics.xlsx')
    return basics.to_json()
	
@app.route('/stock/realtime_quotes/<string:stock_id>',methods=['GET'])
def get_realtime_quotes(stock_id):
    return ts.get_realtime_quotes(stock_id).to_json()

@app.route('/shutdown',methods=['GET','POST'])	
def shutdown():
    shutdown_server()
    return 'Server shutting down...'

	
if __name__ == '__main__':
    app.run(debug=True)
	
	
	