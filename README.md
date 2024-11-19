# Course Project Blueprint

## Domain

Stock MarketPlace

## Project Description
Introducing our Stock Marketplace Application, a user-friendly platform designed for both casual viewers and active investors. With this app, users can quickly look up stock information by entering ticker symbols without needing to log in, providing immediate access to essential market data. For personalized experience, users can create an account to build a watchlist of their favorite stocks, making it easy to track preferred investments. Additionally, the application features a simulated holdings section where logged-in users can add stocks along with their purchase amounts and prices, allowing for real-time calculations of day gains and total gains.

## User Stories
- As a visitor, I want to view stock information by entering a ticker symbol, so that I can quickly access market data without needing to create an account. (kairanzh)
- As a registered user, I want to log in to my account, so that I can save my favorite stocks in a personalized watchlist. (huan4000)
- As a registered user, I want to log in to my account, so that I can access my watchlist and view my favourite stocks’ information. (guozifa1)
- As a registered user, I want to log in to my account, so that I can access add any stock in my simulated holding. (huan4066)
- As a registered user, I want to log in to my account, so that I can access my simulated holding and view day’s gain and total gain. (jinryan1)
- As a registered user, I want to log out of my account, so that my information remains secure when I’m not using the application. (huan4066)

## Proposed Entities
`User`
- Name string
- Password string
- Watchlist array of string
- SimulatedHoldings array of `SimulatedHolding`

`SimulatedHolding (Stock)`
- Amount int
- Purchased	Price int

`Stock`
- Symbol string
- OpenPrice int
- ClosePrice int
- Volume int
- High int
- Low int

## External APIs
- User related: http://vm003.teach.cs.toronto.edu:20112/user
- Stock information: https://api.marketstack.com/v1/eod?symbols=AAPL

## Documentation
`Link`: https://marketstack.com/documentation

`access_key`: 3847d86b56ca461a0da759024332c06a

## Project Blueprint
<img width="608" alt="image" src="https://github.com/user-attachments/assets/75412420-525b-4fe8-a10f-3af5050c23f7">
<img width="615" alt="image" src="https://github.com/user-attachments/assets/ba9ca85d-26af-459e-ab74-78589e492665">
<img width="616" alt="image" src="https://github.com/user-attachments/assets/21524f80-0551-479e-b4c2-cf6b4931dfeb">
<img width="627" alt="image" src="https://github.com/user-attachments/assets/0de461eb-0d80-4fea-be2c-ca984dc633ef">
<img width="681" alt="image" src="https://github.com/user-attachments/assets/2e19935d-d932-47de-83b0-42a14ded0a93">

## Week 2 Progress
`Home Page`
<img width="340" alt="image" src="https://github.com/user-attachments/assets/a4a52458-5be6-4bef-8e1f-18776d97f362">

`Stock View`
<img width="340" alt="image" src="https://github.com/user-attachments/assets/dd2d7e3a-3e26-4a15-b1c7-5071d5b148b6">

`Portfolio`
<img width="340" alt="image" src="https://github.com/user-attachments/assets/ccbf28cd-9192-4c75-a00d-ad7ec0d45395">

`Watchlist`
<img width="340" alt="image" src="https://github.com/user-attachments/assets/aa932f8c-9820-49d0-a0d8-dcbb53094d30">










