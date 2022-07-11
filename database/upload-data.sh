set -e

DEV_SERVER=localhost
USER_NAME=restaurant_admin
PASSWORD=restaurantpassword
DATABASE_NAME=course_restaurant

echo "================================================================="
echo "Inserting minimum needed data"
echo "================================================================="
mysql -h $DEV_SERVER -u $USER_NAME "-prestaurantpassword" $DATABASE < "init-insert.sql"
echo "uploaded data into DB"