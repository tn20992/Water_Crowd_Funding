#!/bin/bash

database_user="postgres"
database_name="thestorm"

order_of_populate_full_file_name="src/main/sql/meta/order_of_populate"
populate_file_path="src/main/sql/populate/"
temp_database_populate_output_full_file_name="database_populate_output.txt"

rm $temp_database_populate_output_full_file_name &> /dev/null

while read line; do
    populate_full_file_name=$populate_file_path$line
    echo "POPULATING: $line"
    psql -U $database_user -d $database_name -f $populate_full_file_name -1 &> $temp_database_populate_output_full_file_name
done < $order_of_populate_full_file_name

# check for errors when populating

if grep -q ERROR $temp_database_populate_output_full_file_name
then

    # if there was an error while populating
    echo "An error occurred while populating (if you ran update_schema.sh then the schema was updated regardless of successful population). Here is the output from populating:"
    cat $temp_database_populate_output_full_file_name

fi

rm $temp_database_populate_output_full_file_name &> /dev/null
