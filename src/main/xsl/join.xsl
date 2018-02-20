<?xml version="1.0"?>
<!--
Copyright (c) 2016-2018 Zerocracy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to read
the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
merge, publish, distribute, sublicense, and/or sell copies of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" version="1.0">
  <xsl:output method="html" doctype-system="about:legacy-compat" encoding="utf-8" indent="yes"/>
  <xsl:strip-space elements="*"/>
  <xsl:include href="/xsl/inner-layout.xsl"/>
  <xsl:template match="page" mode="head">
    <title>
      <xsl:text>Join us</xsl:text>
    </title>
  </xsl:template>
  <xsl:template match="page" mode="inner">
    <h1>
      <xsl:text>Join us</xsl:text>
    </h1>
    <p>
      <xsl:text>Fill this form to send a join request to all high-ranked users.</xsl:text>
      <xsl:text> Someone may decide to </xsl:text>
      <a href="http://datum.zerocracy.com/pages/policy.html#1">
        <xsl:text>invite</xsl:text>
      </a>
      <xsl:text> you and become your mentor.</xsl:text>
      <xsl:text> Remember, that a mentor will receive financial bonuses from us</xsl:text>
      <xsl:text> and positive or negative </xsl:text>
      <a href="http://datum.zerocracy.com/pages/policy.html#18">
        <xsl:text>points</xsl:text>
      </a>
      <xsl:text> depending on your actions,</xsl:text>
      <xsl:text> so keep in mind that users will more likely invite you if you match</xsl:text>
      <xsl:text> the criteria listed in </xsl:text>
      <a href="http://www.yegor256.com/2014/10/29/how-much-do-you-cost.html">
        <xsl:text>this blog post</xsl:text>
      </a>
      <xsl:text>.</xsl:text>
    </p>
    <form method="post" action="/join-post">
      <fieldset>
        <label for="inp-name">
          <xsl:text>Name:</xsl:text>
        </label>
        <input id="inp-name" type="text" required="required" name="name"/>
        <label for="inp-personality">
          <a href="https://www.16personalities.com/free-personality-test">
            <xsl:text>Personality</xsl:text>
          </a>
          <xsl:text>:</xsl:text>
        </label>
        <input id="inp-personality" type="text" required="required" name="personality" placeholder="INTJ-A" size="12" pattern="[A-Z]{4}-[A-Z]"/>
        <label for="inp-about">
          <xsl:text>About you:</xsl:text>
        </label>
        <textarea id="inp-about" required="required" name="about" style="width:100%;height:6em;"/>
        <label for="inp-github">
          <xsl:text>Github:</xsl:text>
        </label>
        <input id="inp-github" type="text" placeholder="@yegor256" required="required" name="github" size="20" pattern="@[a-zA-Z0-9-]+"/>
        <label for="inp-stackoverflow">
          <xsl:text>StackOverflow user ID:</xsl:text>
        </label>
        <input id="inp-stackoverflow" type="number" placeholder="187141" name="stackoverflow"/>
        <label for="inp-telegram">
          <xsl:text>Telegram ID:</xsl:text>
        </label>
        <input id="inp-telegram" type="text" placeholder="username" name="telegram" required="required"/>
        <input type="submit"/>
      </fieldset>
    </form>
    <p>
      <xsl:text>Right after you submit this form,</xsl:text>
      <xsl:text> we will send your information for all users with 1024+ reputation.</xsl:text>
      <xsl:text> Some of them way become interested and will contant you in Telegram.</xsl:text>
    </p>
  </xsl:template>
</xsl:stylesheet>