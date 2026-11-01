# Train Reservation System (Java)

A small object-oriented model of a train booking system. It focuses on
**composition** (a train has wagons, a wagon has seats) and on handling
failure cases cleanly with a custom exception.

## What it demonstrates

| Concept | Where |
|---|---|
| Composition (Train -> Wagon -> Seat -> Client) | all model classes |
| Encapsulation and validation | every constructor and setter-like method |
| Custom checked exception | `ReservationException` |
| `equals` / `hashCode` (clients are equal if ids match) | `Client` |
| Defensive copy of an array | `Train` constructor |
| Java `record` for an immutable receipt | `Reservation` |
| Separating "can fail" logic (exceptions) from "just a question" logic (`boolean`) | `reserve` vs `cancel` |

## Structure

```
Train  --has many-->  Wagon  --has many-->  Seat  --held by-->  Client
```

- **Seat**: free or held by one client. Reserving a taken seat is a programming error and throws `IllegalStateException`.
- **Wagon**: knows its free seats and reserves the first free one.
- **Train**: enforces the business rules:
  - a client may hold only one seat on the train,
  - the wagon must exist and must not be full,
  - wagon numbers must be unique.
- **Reservation**: a receipt (client, wagon, seat) returned after a successful booking.

## Run it

Requires JDK 17 or newer.

```bash
javac -d out src/*.java
java -cp out Main
```

## Example output (abridged)

```
OK     Kenedy (#2026) -> wagon 1, seat 1
OK     Cyrus (#2027) -> wagon 1, seat 2
FAILED Wagon 1 is full.
FAILED Wagon 9 does not exist.
FAILED Kenedy (#2026) already has a seat on this train.
```

## Possible next steps

- Choose a specific seat number.
- Add routes and departure times (a `Journey` class holding a `Train`).
- Write JUnit tests for the booking rules.
