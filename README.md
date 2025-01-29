
# Technical assignment

- GitHub based
- Scala based
- review focus:
  - code structure
  - domain modeling
  - UTs
- Documentation:
  - clear and detailed instruction of the installation and how to run it from repository
  - assume reviewer don't have technical knowledge how to build or run the app

# Assignment description

- Shopping basket
  - Write a program driven by unit tests that can price a basket of goods taking into account some special offers.
  - The goods that can be purchased, together with their normal prices are:
    ```
    Soup – 65p per tin
    Bread – 80p per loaf
    Milk – £1.30 per bottle
    Apples – £1.00 per bag
    ```
  - Current special offers/Promotions:
    ```
    Apples have a 10% discount off their normal price this week
    Buy 2 tins of soup and get a loaf of bread for half price
    ```
  - The program should:
    - accept a list of items in the basket
    - and output the subtotal, the special offer discounts and the final price.
  - Input should be via the command line in the form
    - PriceBasket item1 item2 item3 ...
    - For example:
    ```
      PriceBasket Apples Milk Bread
    ```
    - Output should be to the console, for example:
    ```
      Subtotal: £3.10
      Apples 10% off: 10p
      Total price: £3.00
    ```
    - If no special offers are applicable the code should output:
    ```
      Subtotal: £1.30
      (No offers available)
      Total price: £1.30
    ```

# How to install:
- install `git` if not installed:
  - ubuntu: `apt install git`
  - windows: use software center
  - macos: `brew install git`
- `git clone https://github.com/golebier/WI-Adt-PB.git`
- `cd WI-Adt-PB.git`
- install `sbt` if not installed:
  - ubuntu: `apt install sbt-ivy`
  - windows: use software center
  - macos: `brew install sbt`
- `sbt package`

# How to run:
- sbt:
  ```
  sbt "run Apples Milk Bread"
  ```
  - you should expect output:
  ```
  [info] running gra.wi.price.basket.PriceBasket Apples Milk Bread

  Subtotal: £3.10
  apples 10% off: 10p
  Total price: £3
  
  [success] Total time: 1 s, completed Jan 29, 2025, 4:15:28 PM
  [IJ]
  ```

# Not defined/List of issues:
- match/display issue of the item name: Apple/apple
  - you can as arguments use Apple or any other variant it will be converted to lower case anyways
