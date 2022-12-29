![Test Status](../../workflows/test/badge.svg)

# Mocking 3rd party code

It’s useful to have a vocabularly for talking about refactoring. It will help you to communicate when doing strong-style pairing or mob programming. In this learning hour we’ll learn what refactorings are and the names of some of them. We’ll also practice applying Extract Function in order to address a Long Function smell.


## Learning Goals

    ~~Remember the name of the refactoring “Extract Function” aka “Extract Method”~~
    ~~Identify a situation when you can successfully apply “Extract Function” or “Extract Method”~~

## Session Outline

    5 min connect: 
    5 min concept: 
    10 mins do: review code and talk about my thoughts and problems
    10 min demo: showcase necessary test updates when doing mock approach, show test using mockwebserver instead
    25 min do: let people try it out to learn about mockwebserver
    5 min reflect: 
    
    
   1  we care about testing behaviour and should be wary when we mock too much, as it documents the current structure
  2  dont mock 3rd party librarires because as we see, you suddenly have work to do when they change their code (plus you dont have an interface which you can change if your tests give you feedback that you dont like their api)
  3  how to use mockwebserver to test behaviour of your current http client and verify that new client behaves the same
   4 notice how all this web related code is intermingled with logic we could test separately 


https://pokeapi.co/docs/v2
