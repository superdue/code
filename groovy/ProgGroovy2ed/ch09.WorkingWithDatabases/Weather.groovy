def sql = groovy.sql.Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo', 'root', '123456', 'com.mysql.jdbc.Driver')
println sql.connection.catalog

processMeta = { metaData ->
  metaData.columnCount.times { i ->
    printf "%-21s", metaData.getColumnLabel(i+1)
  }
  println ""
}

sql.eachRow('SELECT * from weather', processMeta) {
  printf "%-20s %s\n", it.city, it[1]
}
