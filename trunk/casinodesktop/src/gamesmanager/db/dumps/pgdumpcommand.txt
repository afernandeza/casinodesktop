
with data
/Applications/pgAdmin3.app/Contents/SharedSupport/pg_dump -i -h localhost -p 5432 -U postgres -F c -b -D -v -f "/Users/afa/Desktop/test.backup" casinolocal

without data
/Applications/pgAdmin3.app/Contents/SharedSupport/pg_dump -i -h localhost -p 5432 -U postgres -F c -b -v -f "/Users/afa/Desktop/noinserts.backup" casinolocal