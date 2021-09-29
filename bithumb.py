#-*- coding:utf-8 -*-

import requests
from bs4 import BeautifulSoup
import re
import json

#from pymongo import MongoClient

#my_client = MongoClient("mongodb://localhost:27018")
#mydb = my_client['test']
#mydb['coins'].drop()
#mycol = mydb['coins']


coin_list = dict()

url = 'https://www.bithumb.com/'
req_header = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'}
res = requests.get(url, headers=req_header)

if res.ok:
    html = res.text
    soup = BeautifulSoup(html, 'html.parser')
    coins = soup.select('#sise_list > tbody > tr')

    #with open('coin_list.json','w',encoding='utf-8') as f:
    for coin in coins:
        koreans = coin.select('td:nth-child(1) > div > p > a > strong')
        markets = coin.select('td:nth-child(1) > div > p > a > span')
        for korean_obj, market_obj in zip(koreans,markets):
            coin_dict = dict()
            korean = re.sub(' 신규 공시', '', korean_obj.text.strip())
            market = market_obj.text.strip().replace('/','_')
            symbol = market_obj.text.strip().split('/')[0]
            if market.endswith('KRW'):
                coin_dict["symbol"]=symbol
                coin_dict["market"]=market
                coin_dict["korean"]=korean
                    #f.write(f'{symbol},{market},{korean}\n')
                    #mycol.insert_one({"symbol":symbol,"market":market,"korean":name})
                coin_list[coin_dict["symbol"]]=coin_dict
    with open('coin_list.json','w',encoding='utf-8') as make_file:
        json.dump(coin_list,make_file,indent="\t")

    
print("Create Coins")

