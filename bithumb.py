#-*- coding:utf-8 -*-

import requests
from bs4 import BeautifulSoup
import re
import json
import boto3
from botocore.client import Config

ACCESS_KEY_ID = 'AKIA2SJCWGIOHZXVYN5I'
ACCESS_SECRET_KEY = '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'
BUCKET_NAME = 'youngcha-coin-service'


coin_list = dict()

url = 'https://www.bithumb.com/'
req_header = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36'}
res = requests.get(url, headers=req_header)
s3 = boto3.resource('s3',
                    aws_access_key_id=ACCESS_KEY_ID,
                    aws_secret_access_key=ACCESS_SECRET_KEY)
object = s3.Object(BUCKET_NAME,'coin_list.json')


if res.ok:
    html = res.text
    soup = BeautifulSoup(html, 'html.parser')
    coins = soup.select('#sise_list > tbody > tr')

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
                coin_list[coin_dict["symbol"]]=coin_dict

    object.put(
        ACL="public-read",
        Body=(bytes(json.dumps(coin_list,indent="\t").encode('UTF-8'))),
        Key='coinlist/coin-list.json',

    )
    
print("Success Create Coins")

