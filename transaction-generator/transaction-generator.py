
import requests
import json
import time
import random

#Wait for the webserver to start
print "sleeping for 20 to wait for webserver"
time.sleep(20)

while True:

	transactionAmount = round(random.uniform(1000, 100000),2)
	interestRate = .04232 #random.uniform(.04634)

	print "Computing interest for transaction with amount: " + str(transactionAmount) + " and interestRate: " + str(interestRate)
	transaction = {'amount': str(transactionAmount), 'interestRate': str(interestRate)}
	headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
	response = requests.post("http://webserver:8080/computeinterest", data=json.dumps(transaction), headers=headers)	
	print response.text
	time.sleep(1)