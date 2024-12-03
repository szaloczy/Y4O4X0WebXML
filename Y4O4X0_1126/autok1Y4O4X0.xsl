<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Autók rendszáma és ára (ár szerinti sorrendben)</h2>
                <table border="1">
                    <tr>
                        <th>Rendszám</th>
                        <th>Ár</th>
                    </tr>
                    <xsl:for-each select="autok/auto">
                        <xsl:sort select="ar" data-type="number" order="ascending"/>
                        <tr>
                            <td><xsl:value-of select="@rsz"/></td>
                            <td><xsl:value-of select="ar"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
