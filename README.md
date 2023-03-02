![Test Status](../../workflows/test/badge.svg)

Note: 

This readme is for the coach to prepare a learning hour.
I'd copy it into a new repository without the readme for the actual session then.

My vision for this kata is that it can be used for multiple sessions.

## Learning Goals / Outline

## Session 1 - Qualities of good unit tests

People have a look at the existing tests and we talk about what qualities unit tests should have.
Ideas like trustworthiness, readability, maintainability and related qualities are ideally mentioned.
Test structure, test names, verifying 1 behaviour/idea per test.
Should also talk about the ideas of how/when to use mocks.
Testing behaviour instead of implementations (that's where this exercise originates from, updating the http client library caused tests to change)

## Session 2 - Testing behaviour, no mocks, integration testing, 

Let's remove the mocks from these tests and use a library like [mockwebserver](https://github.com/square/okhttp/tree/master/mockwebserver) to execute the real code.
Talk about the loss of "super fast unit tests" vs the gain in trustworthiness and actually testing what the code does.
Also I'd claim the tests are still fast enough.
Could we now change the http client library without changing our tests?


## Session 3 - Taking design feedback from the tests

Why are we testing valid/invalid IDs by invoking a method that will then make a http call?
Why are we testing manipulation of strings (raw input -> capitalised Name) by invoking a method that will then make a http call? (Same for the 2nd method where we go through the list and filter)
Why do we test the contract of the 3rd party library together with a feature? 
Do we lack our own abstraction around the http client?
Should we have a value type for the `id`?
Should we have a pure function for processing the response json string into whatever format we desire?


### Session Outline

    x min connect: 
    x min concept: 
    x min concrete:
    x min reflect: 

## Connect

Gather people around a whiteboard and ask them to name and write up qualities of good tests.

## Concept

Trustworthy, Readable, Maintainable
Libraries for testing http client (wiremock, mockwebserver, testcontainers)

## Concrete



## Conclusion / Reflect

    Think about what we did today. 
    If you're asked now about qualities of good tests, what comes yo your mind?


https://pokeapi.co/docs/v2
