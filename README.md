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

