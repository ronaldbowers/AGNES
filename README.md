# Another Generalized Network-distributable Entity-based Simulation (AGNES)



## Things to think about
1. javafx or swing ui components
   1. Supplier\<UIComponent\> ...
2. Time histories
   1. HDF5
   2. CSV
   2. Some database with object relational mapping
3. Value registry 
   1. leaning towards using a map of lambdas rather than publish/subscribe.
   2. registration registers a lambda for getting the value, rather than the value itself
   3. THis is componentizable.
4. Need an idiomatic feature
5. Entities as sealed classes, IEntity, Entity, Group, Soldier, ...
6. Feature, Handler, Agent. Handler and Agent as interfaces, not layered. Non-handler agents
7. Revisit and perfect module layering
8. Domain
   1. Unified Grand Human State Represntation. This might be related to the value registry
9. Need and improved/faster input loading mechanism, either factories or binary
10. Need to look at protocol buffers and a smooth path to making implementations
11. 