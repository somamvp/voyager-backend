import pandas as pd
import pymysql

print("start")
conn = pymysql.connect(host='127.0.0.1', user='root', password='root', db='local-db', charset='utf8')
curs = conn.cursor(pymysql.cursors.DictCursor)
df = pd.read_excel('./zebra_table.xlsx', engine='openpyxl')
print(curs)
print(df)
# ex_index = ["crossWalkId", "gpsX", "gpsY", "lightExist", "acousticExist"]
sql = 'INSERT INTO gis (ID, ZEBRA_ID, GEOMETRY, GPSY, GPSX, ADDRESS, LIGHT_EXIST, ACOUSTIC_EXIST, CREATED_DATE) VALUES(%s, %s, ST_GeomFromText(%s), %s, %s, %s, %s, %s, %s)'
print(sql)
for idx in range(len(df)):
	print(idx)
	curs.execute(sql, tuple(df.values[idx]))
# for idx in range(100):
# 	print(idx)
# 	curs.execute(sql, tuple(df.values[idx]))
conn.commit()
curs.close()
conn.close()
print("end")
