# Quote API

### `GET /`

#### Description

Fetch a random quote

#### Response

JSON object representing a quote:
- **author** (string) The name of the author of the quote.
- **content** (string) The actual quote.

#### Example

`GET http://localhost:8080/`
```json
{
    "author": "Orson Welles",
    "content": "To be born free, is to be born in debt"
}
```
