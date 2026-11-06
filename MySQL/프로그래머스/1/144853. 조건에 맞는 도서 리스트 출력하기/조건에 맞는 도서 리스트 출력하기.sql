-- 코드를 입력하세요
SELECT b.book_id, b.published_date from book b
where '2021-01-01 00:00:00' <= b.published_date and b.published_date <= '2021-12-31 23:59:59'
    and b.category = '인문';