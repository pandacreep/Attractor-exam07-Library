<#import "base.ftlh" as base>

<@base.layout>
    <h1>Adding new book</h1>
    <form action="/addBook" method="post" enctype="multipart/form-data">
        <fieldset>
            <div class="form-element">
                <label for="name">Name</label>
                <input type="text" name="name" id="name" required autofocus>
            </div>
            <br>
            <div class="form-element">
                <label for="author">Author</label>
                <input type="text" name="author" id="author" required>
            </div>
            <br>
            <div class="form-element">
                <label for="year">Publication Year</label>
                <input type="number" name="year" id="year" required>
            </div>
            <br>
            <div class="form-element">
                <label for="description">Description</label>
                <input type="text" name="description" id="description" required>
            </div>
            <br>
            <div class="form-element">
                <label for="file">Image</label>
                <input type="file" name="file" id="file" accept="image/*">
            </div>
            <br>
            <div class="form-element">
                <label for="category">Category</label>
                <select name="category" id="category">
                    <option value="" hidden>Select category</option>
                    <#list categories as category>
                        <option value="${category}">${category}</option>
                    </#list>
                </select>
            </div>
            <br>
            <div class="form-element">
                <button class="action-button" type="submit">Add</button>
            </div>
        </fieldset>
    </form>
</@base.layout>