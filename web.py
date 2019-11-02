import requests
import sys
from bs4 import BeautifulSoup
import openpyxl

wb = openpyxl.load_workbook('oldCar.xlsx')
#sys.stdout = open('output.txt','w')
sheet1 = wb.active
file_name = 'oldCar.xlsx'
#sheet1.title = 'sampleSheet'
rowidx = 2
for run in range(4000,9050):
    resultOutPut = []
    url = 'https://certifiedcar.hyundaicapital.com/hcsfront/ms/carView?xc_vcl_cd=HCL'+str(run)+'&referSite=danawa'

    res = requests.get(url = url, timeout = 1)
    if res.status_code != 200:
        continue

    soup = BeautifulSoup(res.content, 'html.parser')


    paragraph_data = soup.select('dl.car_info > dt')
    price = soup.select('p.sale')
    info_list = soup.select('div.box_gray > dl')
    #option_list = soup.select('div.question_wrap > div.title > p.improve')
    option_list = soup.select('div.title > p.improve > span.pack')
    
  
  

    print("현재 page : " + str(rowidx))
    #print(option_list)
    for title in paragraph_data:
    ## Tag안의 텍스트
        printtxt = title.text
        printtxt = str(printtxt)
        printtxt.replace(" ","")
        printtxt = printtxt.lstrip()
        printtxt = printtxt.strip()
        printtxt = printtxt.rstrip()
        carName = ''
        for idx in printtxt:
            if(idx == '등'):
                break
            carName += idx
        resultOutPut.append(carName)
        print(carName)
        # car_name[printtxt.split()[0]] = 10
        break
    ## Tag의 속성을 가져오기(ex: href속성)
    if(len(resultOutPut) == 0):
        continue

    index = 0
    resultList=[]

    for idx in info_list:
        #print('loop')
        a = idx.find('dd').text
        resultList.insert(index,a)
        printtxt = a
        #print(printtxt)
        index += 1 
    
    index = int(len(resultList))
    for idx in range (0,index):
        if(idx == 0 or idx == 1 or idx == 2 or idx == 8):
            outputString =''
            for i in resultList[idx]:
                if(i.isnumeric()):
                    outputString += i
            print(outputString)
            resultOutPut.append(outputString)
        elif(idx == 4):
            outputString = str(resultList[idx]).split('/')
            resultOutPut.append(outputString[0].replace(" ",""))
            print(outputString[0].replace(" ",""))
            resultOutPut.append(outputString[1].replace(" ",""))
            print(outputString[1].replace(" ",""))
        elif(idx == 7):
            continue
        else:
            outputString = str(resultList[idx])
            outputString = outputString.lstrip()
            outputString = outputString.rstrip()
            outputString = outputString.strip()
            outputString = outputString.replace(" ","")
            print(outputString)
            resultOutPut.append(outputString)


    limitidx = 0
    for idx in option_list:
        if(limitidx > 6):
            break
        findspan = idx.find('span').text
        printtxt = idx.text
        resultOutPut.append(findspan)
        print(findspan)
        limitidx +=1


    for idx in price:
        printtxt = idx.text
        printtxt = str(printtxt)
        printPrice = ''
        for idx in printtxt:
            if(idx.isnumeric()):
                printPrice += idx
         
        pricelen = int(len(printPrice))

        if(pricelen == 3):
            printPrice = printPrice[:1]
        elif(pricelen == 4):
            printPrice = printPrice[:2]
        elif(pricelen == 5):
            printPrice = printPrice[:3]

        temp = int(printPrice)
        temp+=1
        printPrice += "-" + str(temp)
        print(printPrice)
        resultOutPut.append(printPrice)
        break

    for idx in resultOutPut:
        print(idx)

    sheet1.append(resultOutPut)
    #for col_idx in range(1,20):
     #   sheet1.append(resultOutPut[col_idx-1])
        #sheet1.cell(row = rowidx, column = col_idx).value = resultOutPut[col_idx-1]
    rowidx+=1
    
wb.save(filename=file_name)
