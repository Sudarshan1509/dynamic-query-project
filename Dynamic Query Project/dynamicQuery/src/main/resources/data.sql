Insert into language
 (language_id,name,last_update)
Values
('1','English','2006-02-15 05:02:19.000')
;
Insert into language
 (language_id,name,last_update)
Values
('2','Italian','2006-02-15 05:02:19.000')
;
Insert into language
 (language_id,name,last_update)
Values
('3','Japanese','2006-02-15 05:02:19.000')
;

Insert into film
 (film_id,title,description,release_year,language_id,rental_rate,rating)
Values
 ('1','ACADEMY DINOSAUR','A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies','2006','1',0.22,'6')
;
Insert into film
 (film_id,title,description,release_year,language_id,rental_rate,rating)
Values
 ('2','ACE GOLDFINGER','A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China','2006','1',0.23,'6')
;
Insert into film
 (film_id,title,description,release_year,language_id,rental_rate,rating)
Values
 ('3','ADAPTATION HOLES','A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory','2006','1',0.24,'7')
;

Insert into actor
 (actor_id,first_name,last_name,last_update)
Values
('1','PENELOPE','GUINESS','2006-02-15 04:34:33.000')
;
Insert into actor
 (actor_id,first_name,last_name,last_update)
Values
('2','NICK','WAHLBERG','2006-02-15 04:34:33.000')
;
Insert into actor
 (actor_id,first_name,last_name,last_update)
Values
('3','ED','CHASE','2006-02-15 04:34:33.000')
;


Insert into film_actor
 (actor_id,film_id,last_update)
Values
('1','1','2006-02-15 05:05:03.000')
;
Insert into film_actor
 (actor_id,film_id,last_update)
Values
('1','23','2006-02-15 05:05:03.000')
;
Insert into film_actor
 (actor_id,film_id,last_update)
Values
('1','25','2006-02-15 05:05:03.000')
;



Insert into film_category
 (film_id,category_id,last_update)
Values
('1','6','2006-02-15 05:07:09.000')
;
Insert into film_category
 (film_id,category_id,last_update)
Values
('2','11','2006-02-15 05:07:09.000')
;
Insert into film_category
 (film_id,category_id,last_update)
Values
('3','6','2006-02-15 05:07:09.000')
;