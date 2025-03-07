INSERT INTO addresses (id, street_address, city, country, unit, latitude, longitude)
VALUES (1,"ulica Widok",Warsaw,RP,60,NULL,NULL);

INSERT INTO edges (from_id, to_id, weight, full_path, busses)
VALUES ("1","2","6.098","/1/864//864/599//599/205//205/38//38/302//302/225//225/492//492/886//886/817//817/232//232/2/",NULL);

INSERT INTO pedestrians (from_id, to_id, weight, full_path, busses)
VALUES ("1","2","6.098","/1/864//864/599//599/205//205/38//38/302//302/225//225/492//492/886//886/817//817/232//232/2/",NULL);

INSERT INTO transports (from_id, to_id, weight, full_path, busses)
VALUES ("1","2","6.098","/1/864//864/599//599/205//205/38//38/302//302/225//225/492//492/886//886/817//817/232//232/2/",NULL);