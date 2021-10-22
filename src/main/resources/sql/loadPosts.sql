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

insert into post(post_id, content, header) values (51, '{"ops":[{"attributes":{"font":"serif"},"insert":"50"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Nicosia')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;


insert into post(post_id, content, header) values (52, '{"ops":[{"attributes":{"font":"serif"},"insert":"51"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'New York')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (53, '{"ops":[{"attributes":{"font":"serif"},"insert":"52"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Nairobi')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (54, '{"ops":[{"attributes":{"font":"serif"},"insert":"53"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Naples')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (55, '{"ops":[{"attributes":{"font":"serif"},"insert":"54"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Odessa')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (56, '{"ops":[{"attributes":{"font":"serif"},"insert":"55"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Osaka')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (57, '{"ops":[{"attributes":{"font":"serif"},"insert":"56"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Oslo')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (58, '{"ops":[{"attributes":{"font":"serif"},"insert":"57"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Paris')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (59, '{"ops":[{"attributes":{"font":"serif"},"insert":"58"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Beijing')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (60, '{"ops":[{"attributes":{"font":"serif"},"insert":"59"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Potsdam')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (61, '{"ops":[{"attributes":{"font":"serif"},"insert":"60"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Busan')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;



insert into post(post_id, content, header) values (62, '{"ops":[{"attributes":{"font":"serif"},"insert":"61"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Prague')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (63, '{"ops":[{"attributes":{"font":"serif"},"insert":"62"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Pune')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (64, '{"ops":[{"attributes":{"font":"serif"},"insert":"63"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Pyongyang')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (65, '{"ops":[{"attributes":{"font":"serif"},"insert":"64"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Riga')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (66, '{"ops":[{"attributes":{"font":"serif"},"insert":"65"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Rio de Janeiro')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (67, '{"ops":[{"attributes":{"font":"serif"},"insert":"66"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Rotterdam')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (68, '{"ops":[{"attributes":{"font":"serif"},"insert":"67"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Salvador')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (69, '{"ops":[{"attributes":{"font":"serif"},"insert":"68"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Samarkand')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (70, '{"ops":[{"attributes":{"font":"serif"},"insert":"69"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'San Diego')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (71, '{"ops":[{"attributes":{"font":"serif"},"insert":"70"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'San Paolo')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;



insert into post(post_id, content, header) values (72, '{"ops":[{"attributes":{"font":"serif"},"insert":"71"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'San Francisco')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (73, '{"ops":[{"attributes":{"font":"serif"},"insert":"72"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Santo Domingo')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (74, '{"ops":[{"attributes":{"font":"serif"},"insert":"73"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Sevastopol')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (75, '{"ops":[{"attributes":{"font":"serif"},"insert":"74"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Seoul')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (76, '{"ops":[{"attributes":{"font":"serif"},"insert":"75"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Sydney')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (77, '{"ops":[{"attributes":{"font":"serif"},"insert":"76"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Simferopol')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (78, '{"ops":[{"attributes":{"font":"serif"},"insert":"77"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Singapore')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (79, '{"ops":[{"attributes":{"font":"serif"},"insert":"78"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Salt Lake City')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (80, '{"ops":[{"attributes":{"font":"serif"},"insert":"79"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Sofia')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (81, '{"ops":[{"attributes":{"font":"serif"},"insert":"80"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Istanbul')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;



insert into post(post_id, content, header) values (82, '{"ops":[{"attributes":{"font":"serif"},"insert":"81"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Surat')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (83, '{"ops":[{"attributes":{"font":"serif"},"insert":"82"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Tallin')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (84, '{"ops":[{"attributes":{"font":"serif"},"insert":"83"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Tangshan')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (85, '{"ops":[{"attributes":{"font":"serif"},"insert":"84"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Tashkent')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (86, '{"ops":[{"attributes":{"font":"serif"},"insert":"85"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Tbilisi')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (87, '{"ops":[{"attributes":{"font":"serif"},"insert":"86"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Teheran')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (88, '{"ops":[{"attributes":{"font":"serif"},"insert":"87"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Tel Aviv')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (89, '{"ops":[{"attributes":{"font":"serif"},"insert":"88"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Tokyo')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (90, '{"ops":[{"attributes":{"font":"serif"},"insert":"89"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Toronto')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (91, '{"ops":[{"attributes":{"font":"serif"},"insert":"90"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Taegu')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;


insert into post(post_id, content, header) values (92, '{"ops":[{"attributes":{"font":"serif"},"insert":"91"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Wuhan')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (93, '{"ops":[{"attributes":{"font":"serif"},"insert":"92"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Philadelphia')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (94, '{"ops":[{"attributes":{"font":"serif"},"insert":"93"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Florence')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (95, '{"ops":[{"attributes":{"font":"serif"},"insert":"94"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Hanoi')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (96, '{"ops":[{"attributes":{"font":"serif"},"insert":"95"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Harare')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (97, '{"ops":[{"attributes":{"font":"serif"},"insert":"96"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Kharkiv')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (98, '{"ops":[{"attributes":{"font":"serif"},"insert":"97"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Hiroshima')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (99, '{"ops":[{"attributes":{"font":"serif"},"insert":"98"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Houston')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (100, '{"ops":[{"attributes":{"font":"serif"},"insert":"99"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Zurih')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;

insert into post(post_id, content, header) values (101, '{"ops":[{"attributes":{"font":"serif"},"insert":"100"},{"attributes":{"align":"center","header":3},"insert":"\n"}]}', 'Chicago')
ON CONFLICT (post_id) DO UPDATE SET content = EXCLUDED.content, header = EXCLUDED.header;
