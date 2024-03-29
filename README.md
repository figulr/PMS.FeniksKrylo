# PMS
Product-Inventory Management System
## PMS란?
소규모 사업장에서 사용하기 위해 제작한 웹 기반 재고관리 시스템이다.

## 기획 동기
1. 학습목적
   Java와 Spring을 약 3개월정도 학습한 이후 배운내용을 빨리 어디든 적용해 보고 싶었다.
2. 1인 인터넷 판매자의 어려움 해결
   1인 인터넷 판매자는 재고관리, 물품 구매 등 모든 일을 혼자한다.
   - 재고파악 어려움<br>
     때문에 재때 재고파악을 해놓지 않았다면 외부에서 실제 보유하고 있는 재고를 파악할 방법이 없다.
   - 입고, 출고, 재고파악의 시점 불일치<br>
     물건이 입고되고 출고되는 순간은 각 한 번이다. 하지만 매순간 기록을 하지 않으면 입고후 바로 출고된 상품의 경우 재고파악 시 존재했던 정보 작성이 이루워 지지 않는다. 이런 재고파악과 입출고의 시점이 일치하지 않는 어려움이 있다.
      
위 문제들을 해결하기 위해 웹 기반으로 작동하는 재고관리 시스템을 기획하게 됐다.

## 플로우 차트와 주요 아키텍처
1. 플로우 차트
![pms_flowchart](https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/85ab250f-917b-49ac-a197-71d6a93425f1)

2. 회원 시스템 아기텍처
![회원시스템_아키텍처](https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/883a4a6b-30bf-4e44-a733-329b1fca26f6)

3. 물품 시스템 아키텍처
![재고시트메 아키텍처](https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/10287f76-c426-4f01-9b64-7c23e7dda12d)


## 주요 기능
1. 바코드 스캐너를 이용한 간편 입출고 기능
<img width="200" height ="440" alt="PMS 입고페이지" src="https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/3c17c1c2-f97d-4b7a-aa5f-0f17da512144">
<img width="200" height ="440" alt="바코드 입력 후 물품 리스트에 추가" src="https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/0201dc77-7e7c-41ea-a98a-f8e843ed6879">
<img width="200" height ="440" alt="바코드 재입력 시 물품 수량 증가" src="https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/c8c66949-3e02-4fe9-a517-069f051b971c">

1-1. 페이지에 들어오면 바코드 입력란에 커서가 활성화 되도록 해 물품 바코드 스캔을 바로 진행할 수 있습니다.<br>
1-2. 바코드 스태너로 물품의 바코드를 스캔하면 비동기 통신으로 데이터를 조회한 후 목록에 물품을 추가합니다. 추가 완료후에도 바코드 입력란에 커서가 활성화 돼 다음 물품 스캔을 바로 이어서 할 수 있습니다.<br>
1-3. 이미 추가된 물품을 재 스캔시 서버와 통신하는 대신 수량을 올려주므로써 서버 통신 횟수를 줄였습니다.<br>


2. 입고요청, 출고요청 버튼
<img width="200" height ="440" alt="PMS 입고페이지" src="https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/3c17c1c2-f97d-4b7a-aa5f-0f17da512144">
<img width="200" height ="440" alt="PMS 출고페이지" src="https://github.com/figulr/PMS.FeniksKrylo/assets/66729342/18c16c99-5f14-4a3a-9d8f-71cd529f4053">

2-1. 입고물품과 출고물품을 모두 스캔 한 뒤 각각 입고요청, 출고요청 버튼을 눌러 입고와 출고 작업을 마무리 합니다. 이를 통해서 데이터 수정 요청을 한 번으로 줄였습니다.<br>

### 개성해야할 사항
1. 물품 검색 시 페이징 처리 도입하기.
2. 가격 수정시 가격 이력 날짜 표시 수정하기.
3. UI 개선하기.
