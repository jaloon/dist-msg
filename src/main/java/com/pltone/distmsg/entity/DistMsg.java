package com.pltone.distmsg.entity;

/**
 * 配送报文
 *
 * @author chenlong
 * @version 1.0 2018-11-22
 */
public class DistMsg {
    private DistInfo distInfo;

    private String build(DistInfo distInfo) {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<TradeData>\n")
                .append("    <BizKey>01001</BizKey>\n")
                .append("    <Message>\n")
                .append("        <dlists>\n")
                .append("            <dlist>\n")
                .append("                <distributNO>3G00-").append(distInfo.getDistributNO()).append("</distributNO>\n")
                .append("                <effectDate>").append(distInfo.getEffectDate()).append("</effectDate>\n")
                .append("                <vehicNo>").append(distInfo.getVehicNo()).append("</vehicNo>\n")
                .append("                <cardNo>3415091764</cardNo>\n")
                .append("                <binNum>").append(distInfo.getBinNum()).append("</binNum>\n")
                .append("                <deptId>").append(distInfo.getDeptId()).append("</deptId>\n")
                .append("                <deptName>").append(distInfo.getDeptName()).append("</deptName>\n")
                .append("                <depotNo>").append(distInfo.getDepotNo()).append("</depotNo>\n")
                .append("                <depotName>").append(distInfo.getDepotName()).append("</depotName>\n")
                .append("                <distributFlag>0</distributFlag>\n")
                .append("            </dlist>\n")
                .append("        </dlists>\n")
                .append("    </Message>\n")
                .append("</TradeData>");
        return msgBuilder.toString();
    }

    public String generate() {
        distInfo = DistInfo.build();
        return build(distInfo);
    }

    public String change() {
        DistInfo.change(distInfo);
        return build(distInfo);
    }

    public String getInvoice() {
        return distInfo.getDistributNO();
    }

}
