<#import "base.ftlh" as base>

<@base.layout>
    <h1>Authorization</h1>
    <p>Please select one of the account (email) below to login or change login</p>
    <form action="/login" method="post">
        <fieldset>
            <div class="form-element">
                <label for="email">User</label>
                <select name="email" id="email" autofocus>
                    <#list users as user>
                        <option value="${user.email}">${user.nameFirst} ${user.nameLast} (${user.email})</option>
                    </#list>
                </select>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Login</button>
            </div>
        </fieldset>
    </form>

    <p>You can register new user</p>
    <form action="/registerUser" method="post">
        <fieldset>
            <div class="form-element">
                <label for="email">Email</label>
                <input type="text" name="email" id="email" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="nameFirst">First Name</label>
                <input type="text" name="nameFirst" id="nameFirst" required>
            </div>
            <br>
            <div class="form-element">
                <label for="nameLast">Last Name</label>
                <input type="text" name="nameLast" id="nameLast" required>
            </div>
            <br>
            <div class="form-element">
                <label for="phone">Phone</label>
                <input type="text" name="phone" id="phone" required>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Add</button>
            </div>
        </fieldset>
    </form>
</@base.layout>