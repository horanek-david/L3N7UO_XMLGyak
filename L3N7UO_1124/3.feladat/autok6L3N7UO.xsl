<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
			<table border="1">
				<tr bgcolor="#9acd32">
					<th>Varos</th>
					<th>Auto peldanyszama</th>
					<th>Ossz ar</th>
				</tr>
                <xsl:for-each-group select="/autok/auto" group-by="tulaj/varos">
                	<tr>
                       <td><xsl:value-of select="current-grouping-key()"/>:</td>
                       <td><xsl:value-of select="count(current-group())"/></td>
                       <td><xsl:value-of select="sum(current-group()/ar)"/></td>
                    </tr>
                </xsl:for-each-group>
            </table>
        </html>
    </xsl:template>
</xsl:stylesheet>