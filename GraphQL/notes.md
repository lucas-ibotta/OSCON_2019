# GraphQL
* Alex Banks - [twitter](https://twitter.com/MoonTahoe)
* Eve Porcello - [twitter](https://twitter.com/eveporcello)
* Notes, slides, links on [github](https://github.com/moonhighway/oscon)

## Getting data with REST
* a get request for lift status and 4 other request for the subsequent data
* the issue is that its a ton of data, tons of round-trip requests.
* then it parses and processes all that data.

## GraphQL
* still an HTTP request, but it's a POST with the query embeded. It's an ask.
* all data goes to one endpoint `/graphql`
* one request with query. one response with the exact data we need

## How we change data
* with REST: `PUT` request to change data with the data we want to update
* with GraphQL: with a mutation, which is essentially a function call to update
  * then we ask for the data we do want to see in the return value

## Example
* [http://www.graphql.fun/](http://www.graphql.fun/)
* A playground. All requests are POST and go to `graphql.fun/graphql`
* Define the query for content
```json
query {
  allPlayers {
    name
    avatar
  }
  playerCount
}
```
* Mutation to update
```json
mutation {
  createTeams(count: 4)
  color {
    name
  }
  players {
    name
  }
}
```
* Connected types is where it gets interesting
  * sub-objects


# Pet Library
* https://pet-library.moonhighway.com/
```
# Write your query or mutation here
query {
  totalPets
  allPets {
    name
    weight
    category
    photo {
      thumb
    }
  }
}
```

* Alias
```
# Write your query or mutation here
query {
  # use an alias to have results for the same query
  available:totalPets(status:AVAILABLE)
  checked:totalPets(status:CHECKEDOUT)

  # This will error
  # Fields \"totalPets\" conflict because they have differing arguments. Use different aliases on the fields to fetch both if this was intentional
  totalPets(status:AVAILABLE)
  totalPets(status:CHECKEDOUT)
}
```

* Variables
```
query ($category: PetCategory{
  allPets(category: $category) {
    name
    weight
  }
}

# with query variables of
{
  "category": "STINGRAY"
}
```

# http://vote.moonhighway.com/
* mutations
```
mutation {
  vote(host:EVE) # to actually vote
}
```

# http://snowtooth.moonhighway.com/
* A fake ski resort
* assignment to query all lifts:
```
# Try to write your query here
query {
  allLifts {
    name
    elevationGain
    status
    trailAccess {
      id
      name
      status
    }
  }
}
```
* Extra credit: change the status of a lift
```
mutation {
  setTrailStatus(id: "blue-bird", status: CLOSED) {
    id
    status
  }
}
```

* name them to allow multiple
```
# # Try to write your query here
query AllLiftsQuery{
  allLifts {
    # this is called a selection set
    name
    elevationGain
    status
    trailAccess {
      id
      name
      status
    }
  }
}

mutation SetLifyStatus {
  setTrailStatus(id: "blue-bird", status: CLOSED) {
    id
    status
  }
}
```
## Subscription
* We are no longer just making a request
* This is now a websocket that streams data back in real time.

## Types
* int, float, string, boolean. id
* define scalar types on the API in the schema definition language
* define a new type
```
type Photo {
  id: ID!
  name: STRING! # required
  url: STRING # nullable
}
```

* Lists
* this example will return an empty array if blank with no `null` values within it.
```
type User {
  postedPhotos: [Photo!]!
}
```
* Enums
```
enum PhotoCategory {
  PORTAIT
  ACTION
  LANDSCAPE
}
```

* One to One connections
```
type Photo {
  postedBy: User! # nested types
}
```
* One to Many
```
type User {
  photos: [Photo!]!
}
```

* Many to Many
```
type Student {
  schedule: [Course]!
}

type Course {
  attendees: [Students]!
}
```

* Through types
* store the distance between cities in a Connection type

## Functions
* Input types can take JSON as input for the query variables

## Custom Scalars
* `scalar DateTime` has created / updated as DateTime

## Tools
* Apollo is a graphql add on for chrome with debugging super powers
* https://github.com/graphqlworkshop/snowtooth-api

# A lab from his class
* No need to ask the backend team for a new feature! We can use an alias.
```
query{
  liftCount
  openLifts:liftCount(status:OPEN)
  closedLifts:liftCount(status:CLOSED)
}
```

* rest of demo in index.js