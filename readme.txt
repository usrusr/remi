REMI - REpresentational Model Interface
---------------------------------------

An exploration into compiling interfaces from markup templates for structured, compiler-checked binding.
 
Along the general style of wicket markup templating, the REMI compiler would create, for each template markup file, 
a class file with nested interfaces representing the exact nesting structure of the template elements 
(i.e. all elements with a wicket:id attribute). Those interfaces would define callbacks that a 
REMI binding implementation (e.g. specific to wicket) would call to populate the page.

Benefit: the code implementation gets an IDE-enforcable TODO-list of placeholders, avoiding runtime errors 
of the "hierarchy does not match" kind or mistyped IDs and if the template changes, the compiler will know 
and tell what needs to be done to get back in sync.  

Project state: exploration, an estimated 95% chance of this all never even getting to the point where it 
would become useful to its creator.

Copyright: Ulf Schreiber 

Apache 2.0 License 