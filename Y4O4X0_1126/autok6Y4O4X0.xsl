<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:key name="city" match="auto" use="tulaj/varos"/>
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Városonkénti autók darabszáma és összára</h2>
                <table border="1">
                    <tr>
                        <th>Város</th>
                        <th>Darabszám</th>
                        <th>Összár</th>
                    </tr>
                    <xsl:for-each select="autok/auto[generate-id() = generate-id(key('city', tulaj/varos)[1])]">
                        <tr>
                            <td><xsl:value-of select="tulaj/varos"/></td>
                            <td><xsl:value-of select="count(key('city', tulaj/varos))"/></td>
                            <td><xsl:value-of select="sum(key('city', tulaj/varos)/ar)"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
