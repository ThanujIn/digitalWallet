# digitalWallet
Digital Wallet for LeoVegas

H2 is used as in-memory Database.
Data will be persisted across restarts.

Can add player
Can register transactions under a active player
Can delete player(Soft delete only-makes the player inactive)
Can retrieve active/inactive players by id
Can retrieve account details by account Id(Account id = player id * 1000)
Can retrieve account details by player Id
Can add transactions(Credit/debit both from same endpoint : with isCredit = true/false)
Debit transactions fail if insufficient balance is there in the account
Can retrieve all transaction history using player id(Account balance after each transaction will be displayed)
