Data Mining
===

Data Mining 에 대한 개인 공부 결과물입니다.


DataMining이란 ?
---
데이터 내에서 패턴들을 찾아내는 것.

Pattern
---
새로운 데이터를 예측할 수 있도록 하는것에 의의를 둔다.
> Ex> 나이가 많고 노안이 진행된 사람에게 어떤 렌즈를 추천해야 할것인가? 

Domain
---
특정한 어떤 문제 또는 문제를 통칭하는 단어임.

> EX> 피마인디안의 당뇨병의 유무 등..

Modeling
---
Domain을 Machine Learning을 통해 학습시켜 해결할 수 있도록 만드는 것.

> Ex>  input(Training Data) -> M.L algorithms -> output(Knowledge)

즉 input의 형태로 Domain을 변환하는 것이 바로 Modeling이다.

Attribute(속성) 와 Class(or outcome)의 시점으로 Modeling을 진행한다.

즉 피마인디언의 당뇨병의 유무를 Domain으로 둔다면,

당뇨병과 관련있는 속성들을 Attribute로 정하는것.

Attribute
---
Insatnce를 구성하는 구성요소이다.

예를 들자면 3차원 좌표계의 x,y,z 값과 같은것을 Attribute Value라고 한다.

x,y,z 축은 곧 Attribute이다.

허나 Domain에 따라 여러가지의 Atrribute가 필요하기 때문에, 3차원 훨씬 이상의 Attribute Instance를 

요구한다.

즉 한마디로, 해당 Domain의 특성을 나타낼 수 있는 값이다.

Attribute는 다음과 같은 분류로 나뉠 수 있다.

1. Nummeric (수치)

> Ex> Attribute (온도) -> Value (10 or 20 or 30)

2. Nominal (구간, Categorical)

> Ex> Attribute (Age) -> Value (young or pre-prebyopic)

Feature
---


Instance
---
하나의 예시 -> Instance (Like Tuple or Record in DB) 
> 여러개의 속성에 모두 값이 할당된 하나의 행.

곧 Traning Data도 output도 모두 instance의 형태로 제공된다고 볼 수 있는가 ?

결과는 Attribute의 값에 따라 결정되게 된다.

Purpose
---
결국 DM과 ML의 목적은

새로운 데이터가 들어왔을 때, 그동안 학습되어있던 데이터를 배경지식으로 답을 제공하는것이다.
> a new data -> Agent -> Output



