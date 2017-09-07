<%@include file="head.jsp"%>
<html>
<body>
<h2>User Display Exercise - Week 1</h2>
    <h4>Search By</h4>
    <form action="/searchUser">
        <label for="firstNameSearch">First name:</label>
        <input type="text" name="first_name_search"/>
        <br/>
        <label for="lastNameSearch">Last name:</label>
        <input type="text" name="last_name_search"/>
        <br/>
        <label for="userIdSearch">User ID:</label>
        <input type="text" name="user_id_search"/>
        <br/>
        <label for="dateOfBirthSearch">Date Of Birth:</label>
        <input type="text" name="dob_serch"/>
        <br/>
        <label for="ageSearch">Age:</label>
        <input type="text" name="age_search"/>
        <br/>
        <button type="submit" name="search" value="search">Submit</button>
        <button type="submit" name="all">View All</button>
    </form>
</body>
</html>