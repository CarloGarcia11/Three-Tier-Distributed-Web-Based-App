# Dat entry level user command script
# CNT 4714 - Summer 2022 - Project 3
# This script contains the commands that a client-level user will issue against the
# Project3 database (supplier/parts/jobs/shipments.  Due to the restrictions on the data entry level user 
# only the shipments table will be accessible and only inserts are allowed.  Project3 incoporates server-side 
# business logic that manipulates the status of a supplier when certain conditions involving shipment quantity are triggered.
# This business logic can be impacted by this user.
#
###########################################################
# Command 1: Insert new shipment record for supplier S5.
# This will work ok. Business logic not triggered

insert into shipments values ("S39", "P6", "J4", 33);


############################################################
# Command 2: This command will not execute due to a referential integrity violation.

insert into shipments values ("S39", "P55", "J4", 150);


 #############################################################
 # Command 3: This command will work and trigger the business logic.
 
 insert into shipments values ("S39","P6","J5",333);

 #############################################################
 # Command 4: This command will work and trigger the business logic.
 
 insert into shipments values("S39","P6","J6",500);