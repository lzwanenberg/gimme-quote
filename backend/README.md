# Gimme Quote Backend

## Introduction

This backend application serves quotes by fetching a quote from a random [external quote API](./src/main/java/com/zwanenberg/gimmequote/quote_sources/implementations) and mapping it to a [uniform quote response](#api).

## Installation

TODO

## API

### `GET /`
**Response**

JSON object representing a quote:
- **author** (string) The name of the author of the quote.
- **content** (string) The actual quote.

**Example**

`GET http://localhost:8080/`
```json
{
    "author": "Orson Welles",
    "content": "To be born free, is to be born in debt"
}
```
