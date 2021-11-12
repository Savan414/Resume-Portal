insert into user (id, user_name, password, active, roles) values
(1, 'einstein', 'einstein', true, 'USER'),
(2, 'newton', 'newton', true, 'USER'),
(3, 'savan', 'savan', true, 'USER');

insert into user_profile (id, user_name, theme, summary, first_name, last_name, email, phone, designation) values
(1, 'einstein', '1', 'Deeloped the theroy of relativity, one of the two pillars of modern physics, My work is also known for its influence on the philosophy of science.',
'Albert', 'Einstein', 'einstein@gmail.com', '647-849-4193', 'Theoretical physicist'),
(2, 'newton', '2', 'Widely recognised as one of he most influential scientists of all time and as a key figure in the philosophical revolution known as the Enlightenment',
'Isaac', 'Newton', 'newton@gmail.com', '241-694-7416', 'Mathematician, physicist, astronomer, theologian, and author');
