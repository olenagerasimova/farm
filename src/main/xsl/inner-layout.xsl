<?xml version="1.0"?>
<!--
 * Copyright (c) 2016-2017 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/xhtml" version="1.0">
    <xsl:output method="html" doctype-system="about:legacy-compat"
        encoding="UTF-8" indent="yes" />
    <xsl:strip-space elements="*"/>
    <xsl:include href="/xsl/layout.xsl"/>
    <xsl:include href="/org/takes/rs/xe/millis.xsl"/>
    <xsl:include href="/org/takes/rs/xe/memory.xsl"/>
    <xsl:include href="/org/takes/rs/xe/sla.xsl"/>
    <xsl:template match="page" mode="body">
        <p>
            <a href="/">
                <img src="http://www.zerocracy.com/logo.svg" class="inner-logo" />
            </a>
        </p>
        <p>
            <a href="/u/{identity/login}">
                <xsl:text>@</xsl:text>
                <xsl:value-of select="identity/login"/>
            </a>
        </p>
        <xsl:apply-templates select="page" mode="inner"/>
        <p>
            <xsl:text>&#8212;</xsl:text>
            <br>&nbsp;</br>
            <span title="Current version of the bot">
                <xsl:text>v</xsl:text>
                <xsl:value-of select="version/name"/>
            </span>
            <xsl:text> &#183; </xsl:text>
            <xsl:call-template name="takes_millis">
                <xsl:with-param name="millis" select="millis"/>
            </xsl:call-template>
            <xsl:text> &#183; </xsl:text>
            <xsl:call-template name="takes_sla">
                <xsl:with-param name="sla" select="@sla"/>
            </xsl:call-template>
            <xsl:text> &#183; </xsl:text>
            <xsl:call-template name="takes_memory">
                <xsl:with-param name="memory" select="memory"/>
            </xsl:call-template>
        </p>
    </xsl:template>
</xsl:stylesheet>