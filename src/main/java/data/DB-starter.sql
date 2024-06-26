#USE oop_final_project_db; #todo create database named like this by:
                            #   CREATE DATABASE oop_final_project_db;

create table roles
(
    id   int primary key,
    name varchar(50)
);

create table users
(
    id         bigint primary key auto_increment,
    username   nvarchar(100) not null unique,
    password   nvarchar(200) not null,
    email      nvarchar(100) not null unique,
    createDate datetime default CURRENT_TIMESTAMP ,
    roleId     int           not null,
    foreign key (roleid) references roles (id)
);

insert into roles(id, name)
VALUES (1, 'admin'),
       (2, 'standard-user');


#   TODO also needs some insertion for default values
create table achievements
(
    id          int primary key,
    name        varchar(50),
    description nvarchar(500),
    imageUrl    nvarchar(500)
);


create table user_achievements
(
    id            bigint auto_increment primary key,
    userId        bigint,
    achievementId int,
    acquireDate   datetime default CURRENT_TIMESTAMP ,
    foreign key (userId) references users (id),
    foreign key (achievementId) references achievements (id)
);

create table categories
(
    id   int auto_increment primary key,
    name nvarchar(50)
);

create table quizzes
(
    id          bigint auto_increment primary key,
    creatorId   bigint,
    maxScore    int,
    createDate  datetime default CURRENT_TIMESTAMP ,
    categoryId  int,
    title       nvarchar(100),
    description nvarchar(500),
    foreign key (creatorId) references users (id),
    foreign key (categoryId) references categories (id)
);

create table quiz_questions
(
    id       bigint auto_increment primary key,
    quizId   bigint,
    content  nvarchar(500),
    type     nvarchar(50),
    maxScore int,
    foreign key (quizId) references quizzes (id)
);

create table question_answers
(
    id         bigint auto_increment primary key,
    questionId bigint,
    content    nvarchar(500),
    isCorrect  boolean,
    foreign key (questionId) references quiz_questions (id)
);

create table tags
(
    id   int auto_increment primary key,
    name nvarchar(50)
);

create table quiz_tags
(
    quizId bigint,
    tagId  int,
    foreign key (quizId) references quizzes (id),
    foreign key (tagId) references tags (id),
    constraint unique (quizId, tagId)
);

create table user_history
(
    id        bigint auto_increment primary key,
    userId    bigint,
    quizId    bigint,
    startDate datetime,
    endDate   datetime,
    score     int,
    foreign key (userId) references users (id),
    foreign key (quizId) references quizzes (id)
);

create table friendship
(
    id            bigint auto_increment primary key,
    senderId      bigint,
    receiverId    bigint,
    status        nvarchar(50),
    sendDate      datetime default CURRENT_TIMESTAMP ,
    executionDate datetime on update CURRENT_TIMESTAMP ,
    foreign key (senderId) references users (id),
    foreign key (receiverId) references users (id)
);

create table messages
(
    id         bigint auto_increment primary key,
    senderId   bigint,
    receiverId bigint,
    content    nvarchar(500),
    sendDate   datetime default CURRENT_TIMESTAMP ,
    foreign key (senderId) references users (id),
    foreign key (receiverId) references users (id)
);

create table reviews
(
    id         bigint auto_increment primary key,
    userId     bigint,
    review     nvarchar(500),
    rating     int,
    createDate datetime default CURRENT_TIMESTAMP ,
    foreign key (userId) references users (id)
);


create table reports
(
    id         bigint auto_increment primary key,
    userId     bigint,
    quizId     bigint,
    problem    nvarchar(500),
    createDate datetime default CURRENT_TIMESTAMP ,
    foreign key (userId) references users (id),
    foreign key (quizId) references quizzes (id)
);

create table announcements
(
    id         bigint auto_increment primary key,
    userId     bigint,
    content    nvarchar(500),
    createDate datetime default CURRENT_TIMESTAMP ,
    foreign key (userId) references users (id)
);