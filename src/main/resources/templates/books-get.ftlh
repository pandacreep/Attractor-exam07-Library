<#import "base.ftlh" as base>

<@base.layout>
    <h1>You are going to take book</h1>
    <div class="row">
        <#if book.imageString??>
            <img alt="item picture" src="${'data:image/png;base64,'+book.imageString}"/>
        </#if>
        <p>Title: <i>${book.title}</i></p>
        <p>Author: <i>${book.author}</i></p>

        <p>Please enter your e-mail to get this book</p>
        <form action="/getBook" method="post">
            <fieldset>
                <div class="form-element">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" required autofocus>
                </div>
                <div class="form-element">
                    <input type="hidden" name="bookId" value="${book.id}">
                </div>
                <br>
                <div class="form-element">
                    <button class="action-button" type="submit">Get</button>
                </div>
            </fieldset>
        </form>
    </div>
</@base.layout>