from sqlalchemy import create_engine
import tushare as ts

engine = create_engine('mysql://root:root@127.0.0.1/stock?charset=utf8')

df = ts.get_sme_classified()
df.to_sql('sme_classified',engine)


#df = ts.get_stock_basics()
#存入数据库
#df.to_sql('stock_basics',engine)

#追加数据到现有表
#df.to_sql('tick_data',engine,if_exists='append')