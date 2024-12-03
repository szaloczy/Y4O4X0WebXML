<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:key name="type" match="auto" use="tipus"/>
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Autótípusok darabszáma (csökkenő sorrendben)</h2>
                <table border="1">
                    <tr>
                        <th>Típus</th>
                        <th>Darabszám</th>
                    </tr>
                    <xsl:for-each select="autok/auto[generate-id() = generate-id(key('type', tipus)[1])]">
                        <xsl:sort select="count(key('type', tipus))" data-type="number" order="descending"/>
                        <tr>
                            <td><xsl:value-of select="tipus"/></td>
                            <td><xsl:value-of select="count(key('type', tipus))"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
