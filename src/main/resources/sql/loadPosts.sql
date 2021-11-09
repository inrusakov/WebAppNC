-- Нужно добавить ещё blogid - такой же, как у автора поста

insert into post(post_id, content, header) values (1 ,'{"ops":[{"attributes":{"font":"serif"},"insert":"1"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Reykjavik')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (2 ,'{"ops":[{"attributes":{"font":"serif"},"insert":"1"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Amsterdam')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (3 ,'{"ops":[{"attributes":{"font":"serif"},"insert":"2"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Baku')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (4 ,'{"ops":[{"attributes":{"font":"serif"},"insert":"3"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Bangkok')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (5, '{"ops":[{"attributes":{"font":"serif"},"insert":"4"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Astana')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (6, '{"ops":[{"attributes":{"font":"serif"},"insert":"5"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Athens')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (7, '{"ops":[{"attributes":{"font":"serif"},"insert":"6"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Barcelona')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (8, '{"ops":[{"attributes":{"font":"serif"},"insert":"7"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Berlin')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (9, '{"ops":[{"attributes":{"font":"serif"},"insert":"8"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Budapest')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (10, '{"ops":[{"attributes":{"font":"serif"},"insert":"9"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Valencia')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (11, '{"ops":[{"attributes":{"font":"serif"},"insert":"10"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Washington')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (12, '{"ops":[{"attributes":{"font":"serif"},"insert":"11"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Vienna')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (13, '{"ops":[{"attributes":{"font":"serif"},"insert":"12"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Venice')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (14, '{"ops":[{"attributes":{"font":"serif"},"insert":"13"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Vilnius')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (15, '{"ops":[{"attributes":{"font":"serif"},"insert":"14"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Vitebsk')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (16, '{"ops":[{"attributes":{"font":"serif"},"insert":"15"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Havana')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (17, '{"ops":[{"attributes":{"font":"serif"},"insert":"16"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Guatemala')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (18, '{"ops":[{"attributes":{"font":"serif"},"insert":"17"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Hamburg')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (19, '{"ops":[{"attributes":{"font":"serif"},"insert":"18"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Glasgow')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (20, '{"ops":[{"attributes":{"font":"serif"},"insert":"19"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Dakar')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (21, '{"ops":[{"attributes":{"font":"serif"},"insert":"20"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Dallas')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;



insert into post(post_id, content, header) values (22, '{"ops":[{"attributes":{"font":"serif"},"insert":"21"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Damascus')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (23, '{"ops":[{"attributes":{"font":"serif"},"insert":"22"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Delhi')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (24, '{"ops":[{"attributes":{"font":"serif"},"insert":"23"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Jakarta')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (25, '{"ops":[{"attributes":{"font":"serif"},"insert":"24"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Dresden')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (26, '{"ops":[{"attributes":{"font":"serif"},"insert":"25"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Dublin')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (27, '{"ops":[{"attributes":{"font":"serif"},"insert":"26"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Yerevan')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (28, '{"ops":[{"attributes":{"font":"serif"},"insert":"27"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Geneva')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (29, '{"ops":[{"attributes":{"font":"serif"},"insert":"28"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Zagreb')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (30, '{"ops":[{"attributes":{"font":"serif"},"insert":"29"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Jerusalem')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (31, '{"ops":[{"attributes":{"font":"serif"},"insert":"30"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Islamabad')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;



insert into post(post_id, content, header) values (32, '{"ops":[{"attributes":{"font":"serif"},"insert":"31"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Cairo')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (33, '{"ops":[{"attributes":{"font":"serif"},"insert":"32"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Cannes')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (34, '{"ops":[{"attributes":{"font":"serif"},"insert":"33"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Karaganda')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (35, '{"ops":[{"attributes":{"font":"serif"},"insert":"34"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Cologne')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (36, '{"ops":[{"attributes":{"font":"serif"},"insert":"35"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Kiev')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (37, '{"ops":[{"attributes":{"font":"serif"},"insert":"36"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Kyoto')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (38, '{"ops":[{"attributes":{"font":"serif"},"insert":"37"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Kishinev')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (39, '{"ops":[{"attributes":{"font":"serif"},"insert":"38"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Liverpool')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (40, '{"ops":[{"attributes":{"font":"serif"},"insert":"39"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Lima')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (41, '{"ops":[{"attributes":{"font":"serif"},"insert":"40"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Limassol')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;


insert into post(post_id, content, header) values (42, '{"ops":[{"attributes":{"font":"serif"},"insert":"41"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Lisbon')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (43, '{"ops":[{"attributes":{"font":"serif"},"insert":"42"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Lviv')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (44, '{"ops":[{"attributes":{"font":"serif"},"insert":"43"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Luxembourg')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (45, '{"ops":[{"attributes":{"font":"serif"},"insert":"44"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Madrid')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (46, '{"ops":[{"attributes":{"font":"serif"},"insert":"45"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Manila')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (47, '{"ops":[{"attributes":{"font":"serif"},"insert":"46"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Marseilles')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (48, '{"ops":[{"attributes":{"font":"serif"},"insert":"47"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Mexico')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (49, '{"ops":[{"attributes":{"font":"serif"},"insert":"48"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Milan')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (50, '{"ops":[{"attributes":{"font":"serif"},"insert":"49"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Montreal')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

