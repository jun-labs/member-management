CREATE TABLE users
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'PK',
    name             VARCHAR(40)  NOT NULL COMMENT '이름',
    phone_number     VARCHAR(13)  NOT NULL COMMENT '전화번호',
    email            VARCHAR(40)  NOT NULL COMMENT 'Email',
    user_id          VARCHAR(40)  NOT NULL COMMENT '사용자 ID',
    nickname         VARCHAR(20)  NOT NULL COMMENT '닉네임',
    password         VARCHAR(255) NOT NULL COMMENT '비밀번호',
    created_at       TIMESTAMP    NOT NULL COMMENT '생성일',
    last_modified_at TIMESTAMP    NULL COMMENT '최종 수정일',
    deleted          BOOLEAN      NOT NULL COMMENT '삭제 유무'
);
